package com.blibli.future.service;

import com.blibli.future.exception.UnreadableFile;
import com.blibli.future.model.Attendance;
import com.blibli.future.model.primaryKey.AttendanceKey;
import com.blibli.future.repository.AttendanceRepository;
import com.blibli.future.service.api.EmployeeTappingService;
import com.blibli.future.service.api.FileReaderService;
import com.blibli.future.vo.TapData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeTappingServiceImpl implements EmployeeTappingService {
    private class MachineKey{
        private String nik;
        private LocalDate date;

        public MachineKey(String nik, LocalDate date) {
            this.nik = nik;
            this.date = date;
        }

        public MachineKey(TapData tapData) {
            this.nik = tapData.getNik();
            this.date = tapData.getTapDate();
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if(o == null || getClass() != o.getClass()) return false;

            MachineKey that = (MachineKey) o;

            if(nik != null ? !nik.equals(that.nik) : that.nik != null) return false;
            return date != null ? date.equals(that.date) : that.date == null;
        }

        @Override
        public int hashCode() {
            int result = nik != null ? nik.hashCode() : 0;
            result = 31 * result + (date != null ? date.hashCode() : 0);
            return result;
        }
    }

    private AttendanceRepository attendanceRepository;
    private FileReaderService fileReaderService;

    @Autowired
    public EmployeeTappingServiceImpl(AttendanceRepository attendanceRepository, FileReaderService fileReaderService) {
        this.attendanceRepository = attendanceRepository;
        this.fileReaderService = fileReaderService;
    }

    @Override
    public boolean processTapping(String type, String nik, LocalDate dateTap, LocalTime tapTime){
        if(type!=null && tapTime!=null && dateTap!=null && nik!=null){
            if(type.equalsIgnoreCase("in")){
                Attendance attendance = new Attendance(nik, dateTap, tapTime, null);
                attendanceRepository.save(attendance);
            }
            else{
                Attendance attendance = attendanceRepository.findByAttendanceKey(new AttendanceKey(nik, dateTap));
                attendance.setTapOut(tapTime);
                attendanceRepository.save(attendance);
            }
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean processUpdateTapping(String type, String nik, LocalDate dateTap, LocalTime tapTime){
        if(type!=null && tapTime!=null && dateTap!=null && nik!=null){
            if(type.equalsIgnoreCase("in")){
            	Attendance attendance = attendanceRepository.findByAttendanceKey(new AttendanceKey(nik, dateTap));
                attendance.setTapIn(tapTime);
                attendanceRepository.save(attendance);
            }
            else{
                Attendance attendance = attendanceRepository.findByAttendanceKey(new AttendanceKey(nik, dateTap));
                attendance.setTapOut(tapTime);
                attendanceRepository.save(attendance);
            }
            return true;
        }
        else
            return false;
    }

    @Override
    public List<Attendance> processGetTapping(LocalDate dateStart, LocalDate dateEnd){
    	List<Attendance> listAttendance = new ArrayList<>();
        if(dateStart!=null && dateEnd!=null){
        	listAttendance = attendanceRepository.findByAttendanceKeyDateBetween(dateStart, dateEnd);
            return listAttendance;
        }
        return null;
    }

    public boolean createAttendance(TapData tapData){
        Attendance attendance = new Attendance(tapData.getNik(), tapData.getTapDate(), tapData.getTapTime(), null);
        if(attendanceRepository.save(attendance)!=null){
            return true;
        } else {
            return false;
        }
    }

    public List<Attendance> createAttendance(List<TapData> tapDataList) {
        Map<MachineKey, Attendance> attendances = new HashMap<>();

        for(TapData tapData : tapDataList) {
            MachineKey key = new MachineKey(tapData);
            if(attendances.containsKey(key)) {
                attendances.get(key).assign(tapData.getTapTime());
            } else {
                Attendance attendance = new Attendance(tapData.getNik(), tapData.getTapDate(), tapData.getTapTime());
                attendances.put(key, attendance);
            }
        }

        return attendanceRepository.save(attendances.values());
    }

    @Override
    public List<Attendance> addTapMachineFile(MultipartFile tapMachineFile) throws UnreadableFile, DateTimeParseException {
        return createAttendance(fileReaderService.readFileAsListOfTapData(tapMachineFile));
    }
}
