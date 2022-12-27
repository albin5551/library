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


  data: any;
  
//POSTS: any;
page:number=1;
count: any;
tableSize: number = 5;
//tableSizes: any = [3, 6, 9, 12];
ProdData: any;
sortedData: any;
a:any;
b:any;
searchResult:any
searchData:any
sort:string="book_id";
len: any;

  constructor(private userService:UserServiceService) { }

  ngOnInit(): void {

    this.userService.pagenate(this.page,this.tableSize,this.sort).subscribe(response=>{
      this.result=response.content;
      this.count=response.totalElements
      console.log(this.result);
      
    });


    // this.userService.getBook().subscribe(result=>{
    //   this.bookDetail=result;
    //   // console.log(this.bookDetail);
    //   this.count=this.bookDetail.length;
    //   console.log(this.bookDetail);
    //   console.log(this.count)
    // })
  }


  onTableDataChange(event:any) {
    
    console.log(event)
      this.userService.pagenate(event,this.tableSize,this.sort).subscribe((res=>{
        this.result=res.content;
        console.log(this.result)
      }),
      );        
    }

    sortfn(a:any){
    
      this.sort=a;      
      this.page=this.page;
      this.tableSize=5;
      this.ngOnInit();        
  
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
