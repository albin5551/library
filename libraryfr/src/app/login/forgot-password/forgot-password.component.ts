import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthServiceService } from 'src/app/service/auth-service.service';
import { NgToastService } from 'ng-angular-popup';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {


  displayStyle: any;


  ObjForm:FormGroup=new FormGroup({
    sentto:new FormControl('',[Validators.maxLength(50),Validators.required]),
  })

  ObjForgetForm:FormGroup=new FormGroup({
    otp:new FormControl('',[Validators.maxLength(50),Validators.required]),
    newPassword:new FormControl('',[Validators.maxLength(50),Validators.required]),
    cnewPassword:new FormControl('',[Validators.maxLength(50),Validators.required]),
    email:new FormControl()
  })
  email: any;
flag:any=0; 
  constructor(private emails:AuthServiceService,private toast:NgToastService,private router:Router) { }

  ngOnInit(): void {
  }

  SaveData() {
    this.ObjForgetForm.value.email=this.email;
    console.log(this.ObjForgetForm.value.email);
    this.emails.verifyotp(this.ObjForgetForm.value).subscribe(result=>{
    this.otpsuccess();
      this.router.navigate(['/login']);
    })

    
  }
  openPopup() {
    // this.flag=1;
    this.email=this.ObjForm.value.sentto;
    if(this.ObjForm.valid){
    
    this.emails.sendotpw(this.ObjForm.value).subscribe(result=>{

     console.log(result);
     
    })
    this.openSuccess();
    this.displayStyle = "block";
  }
    
  }
  closePopup() {
    this.displayStyle = "none";
  }

  openSuccess(){
    this.flag=1;
    this.toast.success({detail:'OTP Sent Success',summary:'Otp Has been Sent to your mail', duration:5000,position:'tr'})
    }

    otpsuccess(){
      this.toast.success({detail:'Password changed',summary:'Otp Has been Sent to your mail', duration:5000,position:'tr'})
      }

    test(){
      this.openSuccess();
    }

}
