package com.blibli.future.service;

import com.blibli.future.model.Attendance;
import com.blibli.future.repository.AttendanceRepository;
import com.blibli.future.service.api.EmployeeTappingService;
import com.blibli.future.service.api.FileReaderComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service //Question: Is it normal for all method in a service to return a boolean?
public class EmployeeTappingServiceImpl implements EmployeeTappingService{
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
    private class TapData{//Question: Is it better to make this class a public as a VO?
        private String nik;
        private LocalTime tapTime;
        private LocalDate tapDate;

        public TapData(String nik, LocalTime tapTime, LocalDate tapDate) {
            this.nik = nik;
            this.tapTime = tapTime;
            this.tapDate = tapDate;
        }

        public String getNik() {
            return nik;
        }

        public void setNik(String nik) {
            this.nik = nik;
        }

        public LocalTime getTapTime() {
            return tapTime;
        }

        public void setTapTime(LocalTime tapTime) {
            this.tapTime = tapTime;
        }

        public LocalDate getTapDate() {
            return tapDate;
        }

        public void setTapDate(LocalDate tapDate) {
            this.tapDate = tapDate;
        }
    }
    private AttendanceRepository attendanceRepository;
    private FileReaderComponent fileReaderComponent;

    @Autowired
    public EmployeeTappingServiceImpl(AttendanceRepository attendanceRepository, FileReaderComponent fileReaderComponent) {
        this.attendanceRepository = attendanceRepository;
        this.fileReaderComponent = fileReaderComponent;
    }

    @Override
    public boolean processTapping(String type, String nik, LocalDate dateTap, LocalTime tapTime){
        if(type!=null && tapTime!=null && dateTap!=null && nik!=null){
            if(type.equalsIgnoreCase("in")){
                Attendance attendance = new Attendance(nik, dateTap, tapTime, null);
                attendanceRepository.save(attendance);
            }
            else{
                Attendance attendance = attendanceRepository.findOneByNikAndDate(nik, dateTap);
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
            	Attendance attendance = attendanceRepository.findOneByNikAndDate(nik, dateTap);
                attendance.setTapIn(tapTime);
                attendanceRepository.save(attendance);
            }
            else{
                Attendance attendance = attendanceRepository.findOneByNikAndDate(nik, dateTap);
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
        	listAttendance = attendanceRepository.findByDateBetween(dateStart, dateEnd);
            return listAttendance;
        }
        return null;
    }

    public boolean addTapData(TapData tapData){
        Attendance attendance = new Attendance(tapData.getNik(), tapData.getTapDate(), tapData.getTapTime(), null);
        if(attendanceRepository.save(attendance)!=null){
            return true;
        } else {
            return false;
        }
    }

    public boolean addTapData(List<TapData> tapDataList) {
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

        if(attendanceRepository.save(attendances.values()) != null){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addTapMachineFile(MultipartFile tapMachineFile) {
        List<TapData> tapDatas = new ArrayList<>();

        List<String> inputData = fileReaderComponent.readFileAsStrings(tapMachineFile);

        if(inputData == null) {
            return false;
        }

        for(String tappingData : inputData) {
            String[] splitTappingData = tappingData.split(",");

            try {//Which is better? parsing the string here, or create new method here in this class or other class?
                String nik = splitTappingData[0];
                LocalDate tapDate = LocalDate.parse(splitTappingData[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalTime tapTime = LocalTime.parse(splitTappingData[2], DateTimeFormatter.ofPattern("HH:mm:ss"));
                TapData tapData = new TapData(nik, tapTime, tapDate);
                tapDatas.add(tapData);
            } catch (DateTimeParseException e) {
                //Question: LocalDate.parse  will throw an exception if unable to parse the string,
                //          Is it better to catch it here? or should it throw the exception into the controller?
                System.out.println("Unable to parse : " + e.getParsedString());
                e.printStackTrace();
            }
        }

        return addTapData(tapDatas);
    }
}
