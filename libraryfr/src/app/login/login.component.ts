import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthServiceService } from '../service/auth-service.service';
import { UserServiceService } from '../service/user/user-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  resdata: any;
  p="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#\$&*~]).{8,15}$";

  constructor(private authService:AuthServiceService ,private router:Router,private userService:UserServiceService) { }

  login:FormGroup=new FormGroup(
    {
      email:new FormControl('',[Validators.required]),
      password:new FormControl('',[Validators.required,Validators.pattern(this.p)])
    }
  )

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
    this.userService.addUser(this.adduser.value).subscribe(result=>{
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




  onSubmit() {
    if(this.login.valid){
      this.authService.login(this.login.value).subscribe(result=>{
        if(result.userId){
          this.resdata=result;
          // localStorage.setItem('token',this.resdata.accessToken.value);
          // localStorage.setItem('name',this.resdata.name);
          // localStorage.setItem('userid',this.resdata.userId);
          console.log(result);
          

          if(result.role==2){
            localStorage.setItem('token',this.resdata.accessToken.value);
            localStorage.setItem('name',this.resdata.name);
            localStorage.setItem('userid',this.resdata.userId);
            alert("login sucessful");
            this.router.navigate(['/userhome']);
          }

          else{
            localStorage.setItem('token',this.resdata.accessToken.value);
            localStorage.setItem('name',this.resdata.name);
            localStorage.setItem('userid',this.resdata.userId);
            alert("login sucessful");
            this.router.navigate(['/adminhome']);
          }
           
        }
        else{
          alert("login not sucessful");
        }
      })
    }
    }
}
