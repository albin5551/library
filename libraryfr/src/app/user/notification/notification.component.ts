import { Component, OnInit } from '@angular/core';
import { UserServiceService } from 'src/app/service/user/user-service.service';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {

  data:any;
  constructor(private userService:UserServiceService) { }

  ngOnInit(): void {
  this.userService.notfiview().subscribe(result=>{
    this.data=result
    console.log(this.data)

})
  }

  request(id:any){

console.log(id);

    let ag={
    bookId:id
    }
this.userService.request(ag).subscribe(res=>{
  console.log(res);
  if(res){
    alert("request successful")
    window.location.reload();
  }
  
})
  }
  clr(){
    console.log("787878");
    
    this.userService.notificationClear().subscribe();
    // window.location.reload();
  }

}
