import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from 'src/app/service/admin/admin-service.service';
import { NgxPaginationModule } from 'ngx-pagination';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-testview',
  templateUrl: './testview.component.html',
  styleUrls: ['./testview.component.css']
})
export class TestviewComponent implements OnInit {

getServerData($event: Event): any {
throw new Error('Method not implemented.');
}
  result: any;
  bookDetail: any;
  i:any=0;
pageNo:any=1;
sortBy:any="book_id";
paginated:any;
totalrec:any;


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
f:any=0;

data:any

  constructor(private adminService:AdminServiceService) { 
 
  }

  ngOnInit(): void {

    this.adminService.pagenate(this.page,this.tableSize,this.sort).subscribe(response=>{
      this.result=response;
      console.log(this.result);
      this.data=this.result;
      this.totalrec=this.data.length;
      console.log(this.f);
      
      
      
    });

    this.adminService.getBook().subscribe(result=>{
      this.bookDetail=result;
      this.count=this.bookDetail.length;
      console.log(this.bookDetail);
      console.log(this.count)
    })
  }


  sortfn(a:any){
    
    this.sort=a;      
    this.page=this.page;
    this.tableSize=5;
    this.ngOnInit();        
    // console.log(this.f)
    if(a=='book_name')
    this.fun();
    else
    this.fun1();
  }

  fun(){
    this.f=1;
    console.log('...........',this.f);
    
  }
  fun1(){
    this.f=2;
  }


  onTableDataChange(event:any) {
    
    console.log(event)
      this.adminService.pagenate(event,this.tableSize,this.sort).subscribe((result=>{
        this.data=result;
        console.log(this.data)
      }),
      );        
    }
  


  paginate(pageNO:any,pageSize:any,sortBy:any){

    this.i=pageNO;
    this.sortBy=sortBy;
    this.adminService.pagenate(pageNO,pageSize,sortBy).subscribe(
      response=>{
        this.result=response;
        console.log(this.paginated)
      }
    )

  }

  del(id: any) {
    this.adminService.deletBook(id).subscribe(result=>{
      if(result==null)
      console.log(result);
      alert("Delete Successful ")
      window.location.reload();
    })
  }


}
