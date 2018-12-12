import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
//import { TaskDetails } from '../model/TaskDetails';
@Injectable()
export class TaskService {
  constructor(private httpClient: HttpClient) { }
  onTaskSubmit(taskDetails) { 
      const headers = new HttpHeaders({
      'Content-Type': 'application/json'
     });
      return  this.httpClient.post('http://localhost:8085/task-manager/updatetasks',taskDetails,{headers: headers});
  }
  onGetTask(){
      const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.get('http://localhost:8085/task-manager/viewtasks',{headers: headers});
    }
}
