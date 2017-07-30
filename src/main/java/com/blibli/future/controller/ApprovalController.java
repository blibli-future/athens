package com.blibli.future.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blibli.future.service.api.ApprovalService;
import com.blibli.future.vo.ApprovalRequestVo;
import com.blibli.future.vo.ApprovalResponseVo;


@RestController
public class ApprovalController {
	
	public final String BASE_PATH = RequestController.BASE_PATH;
	public final String PATH_APPR_OR_REJECT = RequestController.BASE_PATH + "/{type}/{id}";
	
	private ApprovalService approvalService;
	
	@Autowired
	ApprovalController(ApprovalService approvalService){
		this.approvalService = approvalService;
	}
	
	@PutMapping(PATH_APPR_OR_REJECT)
	public ResponseEntity<String> approving(@PathVariable String type , @PathVariable String id , @RequestBody ApprovalRequestVo approvalRequestVo){
		if(type.equals("leave")){
			approvalService.processLeave(id, approvalRequestVo.getNik(), approvalRequestVo.isApproved());
		}
		else if(type.equals("absence")){
			approvalService.processAbsencePermit(id, approvalRequestVo.getNik(), approvalRequestVo.isApproved());
		}
		else{ return new ResponseEntity<String>("id: "+id+" not processed", HttpStatus.BAD_REQUEST); }
		return new ResponseEntity<String>("id: " + id + " processed", HttpStatus.OK);
	}
	
	@GetMapping(BASE_PATH)
	public ResponseEntity<List<ApprovalResponseVo>> unapprovedList(@RequestParam String chiefNik, @RequestParam String type){
		if(type.equals("history")){
			return new ResponseEntity<List<ApprovalResponseVo>>(approvalService.getRequestHistories(chiefNik), HttpStatus.OK);
		}
		else if(type.equals("unapproved")){
			return new ResponseEntity<List<ApprovalResponseVo>>(approvalService.getUnapprovedRequests(chiefNik), HttpStatus.OK);
		}
		return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
	}
}
