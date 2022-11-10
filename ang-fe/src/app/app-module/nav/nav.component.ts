import { Component, OnInit } from '@angular/core';
import { AppService } from 'src/app/shared/services/app.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {


  title:string = "Job Hunt";
  constructor(private appSerivce :AppService) {
   }

  ngOnInit(): void {
  }

  public toggleSideNav():void{
    this.appSerivce.toggleSidenav();
  }

}
