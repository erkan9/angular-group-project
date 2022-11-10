import { Pipe, PipeTransform } from '@angular/core';
import { JobType } from '../../models/enum/job-type';

@Pipe({
  name: 'jobTypeDisplay'
})
export class JobTypeDisplayPipe implements PipeTransform {

  transform(value: number | JobType | undefined): string {
    if(value == 0) return JobType.FullTime;
    if(value == 1) return JobType.PartTime;
    if(value == 2) return JobType.Remote;
    
    return "";
  }

}
