import { Component, OnInit } from '@angular/core';
import { TaskService } from '../services/task.service';
import { SharedData } from '../shared/shared.data';
import { Router } from '@angular/router';
import { TaskFilter } from '../shared/task.filter';
import { DatePipe } from '@angular/common';
import { TaskDetails } from '../model/TaskDetails';
import { ParentTaskDetails } from '../model/ParentTaskDetails';
@Component({
  selector: 'app-viewtask',
  templateUrl: './viewtask.component.html',
  styleUrls: ['./viewtask.component.css']
})
export class ViewtaskComponent implements OnInit {
  taskList=[];
  filterModel = {
          "task":"",
          "priorityFrom":"",
          "priorityTo":"",
          "parentTask":"",
          "startDate":"",
          "endDate":""
  };
  parentDetails: ParentTaskDetails;
  taskDetails: TaskDetails;
  technicalError : boolean = false;
  screenLoader: boolean = false;
  toDayDate : any;
  constructor(public router: Router,private datepipe:DatePipe,private taskService:TaskService,private sharedData : SharedData) { }
  ngOnInit() {
     this.getTask();
     this.toDayDate =this.datepipe.transform(new Date(),'yyyy-MM-dd');
  }
  updateTask(taskModel){
     this.prepareTaskDetails(taskModel);
     this.sharedData.editTask=this.taskDetails;
     this.router.navigate(['/updatetask']);
  }
  completeTask(taskModel)
  {
     
     this.prepareTaskDetails(taskModel);
     this.taskDetails.endDate=this.datepipe.transform(new Date(),'yyyy-MM-dd');
     this.addTask();
  }
  addTask(){
     this.screenLoader = true;
     this.taskService.onTaskSubmit(this.taskDetails).subscribe(
         (data: any) => {
          this.getTask();
          this.router.navigate(['viewtask']);
          this.screenLoader = false;
        },
         (err: any) => {
          this.technicalError = true; 
          this.screenLoader = false;
          console.log("Backend service not available");
        } 
     );;
  }
  getTask(){    
     this.screenLoader = true;
     this.taskService.onGetTask().subscribe(
      (data:any) => {
         this.taskList = data;
         this.screenLoader = false;
     },
      (err: any) => {
        this.technicalError = true;
        this.screenLoader = false;
        console.log("Backend service not available");
      } 
    );;
   }

  prepareTaskDetails(taskModel){
       this.parentDetails= new ParentTaskDetails('','');
       if(undefined!=taskModel.parentTaskDetail && undefined!=taskModel.parentTaskDetail.parentId && undefined!=taskModel.parentTaskDetail.parentTaskName){
       this.parentDetails.parentId=taskModel.parentTaskDetail.parentId;
       this.parentDetails.parentTaskName=taskModel.parentTaskDetail.parentTaskName;
       }
       this.taskDetails = new TaskDetails(taskModel._id,taskModel.taskId,taskModel.taskName, taskModel.priority, this.parentDetails,taskModel.startDate,taskModel.endDate);
   }
}
