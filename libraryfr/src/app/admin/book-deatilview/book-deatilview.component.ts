import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdminServiceService } from 'src/app/service/admin/admin-service.service';

@Component({
  selector: 'app-book-deatilview',
  templateUrl: './book-deatilview.component.html',
  styleUrls: ['./book-deatilview.component.css']
})
export class BookDeatilviewComponent implements OnInit {
id:any;
bookDetail:any;
  constructor(private adminService:AdminServiceService,private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.id=this.route.snapshot.params['id'];
    this.adminService.getBookbyId(this.id).subscribe(result=>{
      this.bookDetail=result;
      console.log(this.bookDetail);
    })
  }

}
