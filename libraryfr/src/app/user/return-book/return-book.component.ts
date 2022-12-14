import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { UserServiceService } from 'src/app/service/user/user-service.service';

@Component({
  selector: 'app-return-book',
  templateUrl: './return-book.component.html',
  styleUrls: ['./return-book.component.css']
})
export class ReturnBookComponent implements OnInit {

  rentDetails: any;
  id:any;
  rentd: any;
  
  constructor(private userService:UserServiceService) { }

  returnbook:FormGroup=new FormGroup({
    bookId:new FormControl('')
  })

  ngOnInit(): void {

  //  this.id=localStorage.getItem('userid')
    this.userService.rentDetails().subscribe(result=>{
      this.rentDetails=result;
      console.log(this.rentDetails);
      // console.log(this.rentDetails);
    })
  }
  returnBook(rent:any) {
    let data=rent; 
    // this.userService.getbyRentId(data.rentId).subscribe(rs=>{
    //   this.rentd=rs;
    //   console.log(this.rentd);
      
    // })
    this.returnbook.controls['bookId'].setValue(data.book.bookId);
    console.log(data.book.bookId);
    
    // this.returnbook.patchValue({
    //   bookid: data.book.bookId
    // });   
    // this.returnbook.controls['bookId'].setValue(data.book.bookId);

     this.userService.retrun(rent.rentId,this.returnbook.value).subscribe(result=>{
      if(result!=null){
        console.log(result);
        alert("Waiting for confirm");
        window.location.reload();
      }
    })
    }
    


}
