import { Component, OnInit } from '@angular/core';
import { FormControl, FormControlName, FormGroup, Validators } from '@angular/forms';
import { AdminServiceService } from 'src/app/service/admin/admin-service.service';
import { UserServiceService } from 'src/app/service/user/user-service.service';

@Component({
  selector: 'app-book-registration',
  templateUrl: './book-registration.component.html',
  styleUrls: ['./book-registration.component.css']
})
export class BookRegistrationComponent implements OnInit {
  loaddata: any;


  constructor(private adminService:AdminServiceService) { }
  
  bookreg:FormGroup=new FormGroup(
    {
      bookName:new FormControl('',[Validators.required]),
      bookAuthor:new FormControl('',[Validators.required]),
      stock:new FormControl('',[Validators.required]),
      categoryId:new FormControl('',[Validators.required])

    }
  )

  onSubmit() {
    if(this.bookreg.valid){
      this.adminService.addBook(this.bookreg.value).subscribe(result=>{
        if(result.bookId){
          console.log(result);
          alert("Added")
          window.location.reload();
        }
        else{
          alert("not added")
        }
      })
    }
   
    }
  ngOnInit(): void {

    this.adminService.loadCategory().subscribe((data:any)=>{
      this.loaddata=data;
      console.log(this.loaddata);
    })
  }

}
