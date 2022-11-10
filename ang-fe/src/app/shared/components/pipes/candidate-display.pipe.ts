import { Pipe, PipeTransform } from '@angular/core';
import { CandidateStatus } from '../../models/enum/candidate-status';

@Pipe({
  name: 'candidateDisplay'
})
export class CandidateDisplayPipe implements PipeTransform {

  transform(value: number): string {
    if(value == 0) return "Pending";
    if(value == 1) return "Approved";
    if(value == 2) return "Rejected";
    
    return "";
  }

}
