import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbDatepickerModule, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ngbPositioning } from '@ng-bootstrap/ng-bootstrap/util/positioning';
import { NotificationComponent } from '../notification/notification.component';
import { MatBadgeModule} from '@angular/material/badge'
import { UserServiceService } from 'src/app/service/user/user-service.service';
@Component({
  selector: 'app-nav-user',
  templateUrl: './nav-user.component.html',
  styleUrls: ['./nav-user.component.css']
})
export class NavUserComponent implements OnInit {

  constructor(private router:Router,private matdialog:MatDialog,private userService:UserServiceService) { }
count:any;
  ngOnInit(): void {
    this.userService.notfiview().subscribe(res=>{
      this.count=res.length;
      console.log(this.count);
      
    })
  }

  logout() {
    this.router.navigate(['/login']);
    localStorage.clear();
    }
open(){
  this.matdialog.open(NotificationComponent,{
    width:'20%',
    height:'30%',
    position:{left:'30px',top:'80px'}
    
  })
}



    
}
