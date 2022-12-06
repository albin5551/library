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
sortBy:any="bookId";
paginated:any;
  bookDetail: any;
  constructor(private adminService:AdminServiceService) { }

  ngOnInit(): void {
this.adminService.pagenate(0,5,"bookId").subscribe(response=>{
  this.result=response;
  console.log(this.result);
  
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

}
