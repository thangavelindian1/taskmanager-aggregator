import { Component, OnInit } from '@angular/core';
import { TaskDetails } from '../model/TaskDetails';
import { ParentTaskDetails } from '../model/ParentTaskDetails';
import {TaskService} from '../services/task.service';
import {SharedData} from '../shared/shared.data';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
@Component({
  selector: 'app-updatetask',
  templateUrl: './updatetask.component.html',
  styleUrls: ['./updatetask.component.css']
})
export class UpdatetaskComponent implements OnInit {
    parentDetails: ParentTaskDetails;
    taskDetails: TaskDetails;
    parentTaskList=[];
    //submitted: boolean = false;
    endDateRange: boolean = true;
    invalidParent: boolean = false;
    mandatoryFieldsAvail: boolean = true;
    technicalError : boolean = false;
    screenLoader : boolean = false;
    taskNameAlreadyExist : boolean = false;
   constructor(public router: Router,private datepipe:DatePipe,private taskService:TaskService,private sharedData : SharedData) { }
   ngOnInit()
    {
      this.initializeTaskModelFormObject();
      this.getTask();
    }
  onSubmit()
   { 
      this.reInitializeValidateBoolean();
      this.mandatoryFieldsAvailable();
      this.checkTaskNameExist();
      if(this.mandatoryFieldsAvail && !this.taskNameAlreadyExist){
          this.endDateGTStartDate();
          if(this.endDateRange){
              this.getTaskIDOfParent();
              if(!this.invalidParent){
                 this.addTask();
               }
           }
      }
   }
  resetForm()
   {
       this.initializeTaskModelFormObject();
       this.reInitializeValidateBoolean();
   }
  getTaskIDOfParent()
  {
     
     if(this.taskDetails.parentTaskDetail.parentTaskName){
          this.parentTaskList.filter(task=> {
              if (null!=task.taskName && task.taskName.toLowerCase().indexOf(this.taskDetails.parentTaskDetail.parentTaskName.toLowerCase()) !== -1) 
                 {
                   this.taskDetails.parentTaskDetail.parentId=task.taskId;
                 }
           });
            if(""==this.taskDetails.parentTaskDetail.parentId){
               this.invalidParent=true;
            }
     }
  }
   initializeTaskModelFormObject()
   {
      if(undefined!=this.sharedData.editTask){
          this.initializeEditTaskModelFormObject();
      }else{
          this.initializeAddTaskModelFormObject();
      }
   }
  initializeAddTaskModelFormObject()
  {
      this.parentDetails= new ParentTaskDetails('','');
      this.taskDetails = new TaskDetails(null,null,null, null, this.parentDetails, null,null);
     
  }
  initializeEditTaskModelFormObject()
  {
     this.taskDetails=this.sharedData.editTask;
     //this.sharedData.editTask=null;
  }
  endDateGTStartDate()
  {
      this.endDateRange = new Date(this.taskDetails.endDate) >= new Date(this.taskDetails.startDate);
  }
 addTask(){
      this.screenLoader = true;
      this.taskService.onTaskSubmit(this.taskDetails).subscribe(
      (data: any) => {
         this.router.navigate(['/viewtask']);
     },
      (err: any) => {
          this.technicalError = true;
          this.screenLoader = false;
     } 
    );;
   }
  getTask(){
     this.taskService.onGetTask().subscribe(
      (data:any) => {
         this.parentTaskList = data;
     },
      (err: any) => {
        console.log("Backend service not available");
      } 
    );;
   }
  reInitializeValidateBoolean(){
      //this.submitted = true;
      this.invalidParent = false;
      this.endDateRange = true;
      this.mandatoryFieldsAvail = true;
      this.technicalError = false;
      this.taskNameAlreadyExist = false;
   }
   mandatoryFieldsAvailable(){
       if(!this.taskDetails.taskName || !this.taskDetails.startDate || !this.taskDetails.endDate){
         this.mandatoryFieldsAvail = false;
       }
   }
  returnViewTask(){
     this.router.navigate(['/viewtask']);
  }
  checkTaskNameExist(){
    this.parentTaskList.filter(task=> {
              if (null!=task.taskName && null==this.taskDetails.taskId && null!=this.taskDetails.taskName && task.taskName.toLowerCase().indexOf(this.taskDetails.taskName.toLowerCase()) !== -1) 
                 {
                   this.taskNameAlreadyExist = true;
                 }
           });
    }
}
