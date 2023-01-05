import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from 'src/app/service/admin/admin-service.service';

@Component({
  selector: 'app-viewpg-test',
  templateUrl: './viewpg-test.component.html',
  styleUrls: ['./viewpg-test.component.css']
})
export class ViewpgTestComponent implements OnInit {
result:any;
i:any=0;
pageNo:any=0;
sortBy:any="book_id";
paginated:any;
  bookDetail: any;
tableSize: number=8;
page: number=1
count: any;
  constructor(private adminService:AdminServiceService) { }

  ngOnInit(): void {
this.adminService.pagenate(this.page,this.tableSize,this.sortBy).subscribe(response=>{
  this.result=response.content;
  this.count=response.totalElements;
  console.log(this.result);
  console.log(this.count)
  
});

// this.adminService.getBook().subscribe(result=>{
//   this.bookDetail=result;
//   console.log(this.bookDetail);
// })

  }

  // paginate(pageNO:any,pageSize:any,sortBy:any){

  //   this.i=pageNO;
  //   this.sortBy=sortBy;
  //   this.adminService.pagenate(pageNO,pageSize,sortBy).subscribe(
  //     response=>{
  //       this.result=response;
  //       this.count=response.toalElements;
  //       console.log(this.paginated)
  //     }
  //   )

  // }

  onTableDataChange(event:any) {

    this.adminService.pagenate(event,this.tableSize,this.sortBy).subscribe(
      response=>{
        this.result=response.content;
        // this.count=response.toalElements;
        console.log(this.paginated)
      }
    )
         
    
  }
  
  

}
