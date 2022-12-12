import { Component, OnInit } from '@angular/core';
import { UserServiceService } from 'src/app/service/user/user-service.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  result: any;
  bookDetail: any;
  sortBy: any="book_id";
  i: any;
  paginated: any;

  constructor(private userService:UserServiceService) { }

  ngOnInit(): void {

    this.userService.pagenate(0,5,"book_id").subscribe(response=>{
      this.result=response;
      console.log(this.result);
      
    });


    this.userService.getBook().subscribe(result=>{
      this.bookDetail=result;
      console.log(this.bookDetail);
    })
  }

  paginate(pageNO:any,pageSize:any,sortBy:any){

    this.i=pageNO;
    this.sortBy=sortBy;
    this.userService.pagenate(pageNO,pageSize,sortBy).subscribe(
      response=>{
        this.result=response;
        console.log(this.result)
      }
    )

    }

    
    rent(id: any) {
      let data=id;
     this.userService.rentBook(data).subscribe(result=>{
      if(result){
        console.log(result);
        alert("rented");
      }
     })
      }
   
   
    }
