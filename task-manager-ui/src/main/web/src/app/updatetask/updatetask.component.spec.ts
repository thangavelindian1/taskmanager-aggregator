import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { UpdatetaskComponent } from './updatetask.component';
import { ScreenFreezeComponent } from '../screenfreeze/screenfreeze.component';
import { TaskService } from '../services/task.service';
import { SharedData } from '../shared/shared.data';
import { DatePipe } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule  } from '@angular/router/testing';
import { getTask } from '../unit-test/mockResponse/getTask';
import { FormsModule } from '@angular/forms';
describe('UpdatetaskComponent', () => {
   let specObj: any = {};
  let component: UpdatetaskComponent;
  let fixture: ComponentFixture<UpdatetaskComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdatetaskComponent , ScreenFreezeComponent ] ,
      providers: [TaskService,SharedData,DatePipe],
      imports: [BrowserModule,HttpClientModule,RouterTestingModule,FormsModule ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
   // specObj.task = getTask;
    specObj.parentTaskList=getTask;
    fixture = TestBed.createComponent(UpdatetaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
