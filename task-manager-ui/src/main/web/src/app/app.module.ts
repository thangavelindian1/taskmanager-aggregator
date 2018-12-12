import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ViewtaskComponent } from './viewtask/viewtask.component';
import { UpdatetaskComponent } from './updatetask/updatetask.component';
import {TaskService} from './services/task.service';
import {SharedData} from './shared/shared.data';
import { TaskFilter } from './shared/task.filter';
import { HttpClientModule } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { ScreenFreezeComponent } from './screenfreeze/screenfreeze.component';
@NgModule({
  declarations: [
    AppComponent,
    ViewtaskComponent,
    UpdatetaskComponent,
    TaskFilter,
    ScreenFreezeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [TaskService,SharedData,DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
