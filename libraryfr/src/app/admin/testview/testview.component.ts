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
pageNo:any=0;
sortBy:any="bookId";
paginated:any;
totalrec:any;

data:Array<any> 

  constructor(private adminService:AdminServiceService) { 
    this.data=new Array<any>
  }

  ngOnInit(): void {

    this.adminService.pagenate(0,5,"bookId").subscribe(response=>{
      this.result=response;
      console.log(this.result);
      this.data=this.result;
      this.totalrec=this.data.length;
      
    });

    this.adminService.getBook().subscribe(result=>{
      this.bookDetail=result;
      console.log(this.bookDetail);
    })
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
