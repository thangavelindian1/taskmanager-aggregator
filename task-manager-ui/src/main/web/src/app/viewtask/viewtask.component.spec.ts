import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { BrowserModule } from '@angular/platform-browser';
import { ViewtaskComponent } from './viewtask.component';
import { DatePipe } from '@angular/common';
import { ScreenFreezeComponent } from '../screenfreeze/screenfreeze.component';
import { TaskFilter } from '../shared/task.filter';
import {TaskService} from '../services/task.service';
import {SharedData} from '../shared/shared.data';
import { HttpClientModule } from '@angular/common/http';
import { TaskDetails } from '../model/TaskDetails';
import { ParentTaskDetails } from '../model/ParentTaskDetails';
import { RouterTestingModule  } from '@angular/router/testing';
import { getTask } from '../unit-test/mockResponse/getTask';
import { FormsModule } from '@angular/forms';
describe('ViewtaskComponent', () => {
  let specObj: any = {};
  let component: ViewtaskComponent;
  let fixture: ComponentFixture<ViewtaskComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewtaskComponent,TaskFilter, ScreenFreezeComponent ],
      providers: [TaskService,SharedData,DatePipe],
      imports: [BrowserModule,HttpClientModule,RouterTestingModule,FormsModule ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    //specObj.task = getTask;
    specObj.taskList = getTask;
    fixture = TestBed.createComponent(ViewtaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
