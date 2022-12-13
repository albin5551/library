import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AdminServiceService } from 'src/app/service/admin/admin-service.service';

@Component({
  selector: 'app-return-view',
  templateUrl: './return-view.component.html',
  styleUrls: ['./return-view.component.css']
})
export class ReturnViewComponent implements OnInit {

  rentDetails: any;




  
  constructor(private adminService:AdminServiceService) { }
  returnbook:FormGroup=new FormGroup({
    bookId:new FormControl('')
  })
  ngOnInit(): void {
    this.adminService.retrunview().subscribe(result=>{
      this.rentDetails=result
    })
  }

  returnVerify(id: any) {
    let data=id;
    this.returnbook.controls['bookId'].setValue(data.book.bookId);
    this.adminService.returnApprove(data.rentId,this.returnbook.value).subscribe(result=>{
      if(result!=null){
        console.log(result);
        alert(" confirmed");
      }
    })
}
}
