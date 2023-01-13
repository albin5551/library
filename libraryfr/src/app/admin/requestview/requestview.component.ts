import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AdminServiceService } from 'src/app/service/admin/admin-service.service';
import { UserServiceService } from 'src/app/service/user/user-service.service';

@Component({
  selector: 'app-requestview',
  templateUrl: './requestview.component.html',
  styleUrls: ['./requestview.component.css']
})
export class RequestviewComponent implements OnInit {


  constructor(private adminService: AdminServiceService,private userService:UserServiceService) { }
  data: any;
  b: any

  aa: FormGroup = new FormGroup({
    bo: new FormControl('')
  })
  ngOnInit(): void {
    this.adminService.requestview().subscribe(res => {
      console.log(res);
      this.data = res

    })
  }

  returnVerify(d: any) {
    let id = d.requestId;

    // this.aa.controls['bo'].setValue(d.book.bookId)
    let dd = {
      bookId: d.book.bookId
    }
    let rent={
      userId:d.user.userId,
      bookId: d.book.bookId
    }
    this.adminService.requestApprove(id, dd).subscribe(res => {
      console.log(res);
      
    })
this.rent(rent);

  }

  rent(id: any) {
    let data=id;
   this.userService.rentBook(data).subscribe(result=>{
    if(result){
      console.log(result);
      alert("requestapproved");
    }
   })

}
}
