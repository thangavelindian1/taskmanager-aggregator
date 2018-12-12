import {ParentTaskDetails} from "./ParentTaskDetails";
export class TaskDetails {
  constructor(
    public _id: any,
    public taskId: any,
    public taskName: any,
    public priority: string,
    public parentTaskDetail: ParentTaskDetails,
    public startDate: string,
    public endDate:string
  ) {  }

}