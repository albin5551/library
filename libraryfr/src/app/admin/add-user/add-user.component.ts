import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserServiceService } from 'src/app/service/user/user-service.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {


  p="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#\$&*~]).{8,15}$";
  constructor(private router:Router,private userService:UserServiceService) { }

  adduser:FormGroup=new FormGroup({
    name:new FormControl('',[Validators.required]),
    email:new FormControl('',[Validators.required]),
    password:new FormControl('',[Validators.required,Validators.pattern(this.p)]),
    address:new FormControl('',[Validators.required]),
    phone: new FormControl('',[Validators.required])
  })

  ngOnInit(): void {
  }

    add() {
    if(this.adduser.valid){
      
    this.userService.addUser(this.adduser.value).subscribe((result)=>{

    
      if(result==null){
        alert("User alreday exist")
      }
      else{
      console.log(result);
      alert("user added");
      window.location.reload();
      }
        
    
    })
    }
    }


}
