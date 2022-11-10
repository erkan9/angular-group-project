import { Pipe, PipeTransform } from '@angular/core';
import { JobCategory } from '../../models/enum/job-category';

@Pipe({
  name: 'jobCategoryDisplay'
})
export class JobCategoryDisplayPipe implements PipeTransform {

  transform(value: number | JobCategory | undefined): string {
    if(value == 0) return JobCategory.Development;
    if(value == 1) return JobCategory.OfficeAdministration;
    
    return "";
  }

}
