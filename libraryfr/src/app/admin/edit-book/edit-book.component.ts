import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminServiceService } from 'src/app/service/admin/admin-service.service';

@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.css']
})
export class EditBookComponent implements OnInit {
  loaddata: any;
  selectedFiles: any;
  currentFile: any;

  constructor(private route:ActivatedRoute,private adminService:AdminServiceService, private router:Router) { }

editbook:FormGroup=new FormGroup({
  bookName:new FormControl(''),
  bookAuthor:new FormControl(''),
  stock:new FormControl(''),
  categoryId:new FormControl('')
})



onSubmit(){
  let data={
    bookName:this.editbook.controls['bookName'].value,
    bookAuthor:this.editbook.controls['bookAuthor'].value,
    stock:this.editbook.controls['stock'].value,
    categoryId:this.editbook.controls['categoryId'].value

    
  }


  if(this.editbook.valid){
    this.adminService.updateBook(this.id,data).subscribe(result=>{
      if(result){
        console.log(result);
        this.upload()
        alert("Updated");
        this.router.navigate(['/testview'])
      }
    })
  }
}

  id:any;
  bookDetail:any;
  ngOnInit(): void {
  this.id=this.route.snapshot.params['id'];
  this.adminService.getBookbyId(this.id).subscribe(result=>{
    this.bookDetail=result;

    this.editbook.controls['categoryId'].setValue(result.categoryId.categoryId)
    this.editbook.controls['bookName'].setValue(result.bookName)
    this.editbook.controls['bookAuthor'].setValue(result.bookAuthor)
    this.editbook.controls['stock'].setValue(result.stock)

    console.log(this.bookDetail);
  })
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
      console.log(this.id)
      this.adminService.imageUpload(this.currentFile,this.id).subscribe(
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
