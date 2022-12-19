import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from 'src/app/service/admin/admin-service.service';
import { NgxPaginationModule } from 'ngx-pagination';
import { PageEvent } from '@angular/material/paginator';
import { FormControl, FormControlName, FormGroup, Validators } from '@angular/forms';

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
key:any;


data:any

  constructor(private adminService:AdminServiceService) { 
 
  }
  search:FormGroup=new FormGroup({
    inp:new FormControl('',[Validators.required])
  })

  ngOnInit(): void {

       this.key=this.search.controls['inp'].value
      // console.log('````````````````',this.key);


    this.adminService.search(this.key,this.page,this.tableSize,this.sort).subscribe(response=>{
      this.result=response.content;
      console.log(this.result);
      this.data=this.result;
      this.totalrec=this.data.length;
      console.log(this.f);
      
      
      
    });
  

    if(this.key==""){
    this.adminService.getBook().subscribe(result=>{
      this.bookDetail=result;
      this.count=this.bookDetail.length;
      console.log(this.bookDetail);
      console.log(this.count)
    })
  }
  else{
    this.adminService.search(this.key,this.page,this.tableSize,this.sort).subscribe(response=>{
      // this.result=response;
      // console.log(this.result);
      // this.data=this.result;
       this.count=response.content.pageable.totalElements;
       console.log("llllllll",this.count);
       
      // console.log(this.f);
      
      
      
    });
    
  }
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

  ser(){
  
    this.ngOnInit();
  }

  fun(){
    this.f=1;
    console.log('...........',this.f);
    
  }
  fun1(){
    this.f=2;
  }


  onTableDataChange(event:any) {
    this.adminService.search(this.key,event,this.tableSize,this.sort).subscribe(response=>{
      this.result=response.content;
      this.data=this.result;
      console.log(this.data);
      

      
      
      
    });        
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
