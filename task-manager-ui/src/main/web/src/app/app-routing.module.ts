import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ViewtaskComponent }  from './viewtask/viewtask.component';
import { UpdatetaskComponent }  from './updatetask/updatetask.component';
const routes: Routes = [
{ path: '', redirectTo:'/viewtask',pathMatch:'full'},
{ path: 'viewtask', component: ViewtaskComponent },
{ path: 'updatetask', component: UpdatetaskComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
