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
  selectedFiles: any;
  currentFile: any;
  data: any;


  constructor(private adminService:AdminServiceService) { }
  
  bookreg:FormGroup=new FormGroup(
    {
      bookName:new FormControl('',[Validators.required]),
      bookAuthor:new FormControl('',[Validators.required]),
      stock:new FormControl('',[Validators.required]),
      categoryId:new FormControl('',[Validators.required])

    }
  )

a={
  'message':'eee'
}
  onSubmit() {
    if(this.bookreg.valid){
      this.adminService.addBook(this.bookreg.value).subscribe(result=>{
        if(result.bookId){
          this.data=result
          console.log(result);
          this.upload()
          alert("Added")
          // let msg="NewBook"+result.bookName;
          let ag={
            message:"Newbook "+result.bookName+" is added",
            bookId:result.bookId
          }
          console.log(ag)
          this.adminService.notification(ag).subscribe()
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

  selectFile($event:any) {
    this.selectedFiles=$event.target.files;
  } 

  upload()
  {
  
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);

      if (file) {
        this.currentFile = file;
      }
      //this.assi.assignmentId=
      //this.assi.studentId=this.studentDetails.studentId
      // this.assi.file=this.currentFile;
      console.log(this.data.bookId)
      this.adminService.imageUpload(this.currentFile,this.data.bookId).subscribe(
      response =>{
        console.log(response)

        if(!response)
        {
          
          alert("IMAGE UPLOADED SUCCESSFULLY")
        }

      }, error => { alert("Invalid Data\t " +error.HttpErrorResponse+"  Status" +error.status) 
    }
    )}
  
  }

}
