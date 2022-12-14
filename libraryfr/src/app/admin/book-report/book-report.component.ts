import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from 'src/app/service/admin/admin-service.service';

@Component({
  selector: 'app-book-report',
  templateUrl: './book-report.component.html',
  styleUrls: ['./book-report.component.css']
})
export class BookReportComponent implements OnInit {

name() {
throw new Error('Method not implemented.');
}

  result: any;
  bookDetail: any;
  i:any=0;
pageNo:any=0;
sortBy:any="bookId";
paginated:any;



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
sort:string="bookId";
len: any;

  constructor(private adminService:AdminServiceService) { }

  ngOnInit(): void {


    this.adminService.getBook().subscribe(result=>{
      this.bookDetail=result;
      this.count=this.bookDetail.length;
      console.log(this.bookDetail);
      console.log(this.count)
    })


    this.adminService.pagenate(this.page,this.tableSize,this.sort).subscribe(response=>{
      this.data=response;
      console.log(this.result);
      
    });

  
    







    // if(this.searchData==null || this.searchData==""){
    //   this.adminService.pagenate(this.page,this.tableSize,this.sort).subscribe((result=>{
    //     this.data=result; 
    //     console.log(this.data)         
    //   }));        
    // }
    // else{

    //   this.data=this.searchData
    // }    
  


    
  }

  sortfn(a:any){
    
    this.sort=a;      
    this.page=this.page;
    this.tableSize=5;
    this.ngOnInit();        

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
        console.log(this.paginate)
      }
    )
  }

  

}
