import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminServiceService } from 'src/app/service/admin/admin-service.service';

@Component({
  selector: 'app-view-bookreg',
  templateUrl: './view-bookreg.component.html',
  styleUrls: ['./view-bookreg.component.css']
})
export class ViewBookregComponent implements OnInit {

  bookDetail:any;
  constructor(private adminService:AdminServiceService) { }

  ngOnInit(): void {

    this.adminService.getBook().subscribe(result=>{
      this.bookDetail=result;
      console.log(this.bookDetail);
    })
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
