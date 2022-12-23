import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AdminServiceService } from 'src/app/service/admin/admin-service.service';

@Component({
  selector: 'app-return-view',
  templateUrl: './return-view.component.html',
  styleUrls: ['./return-view.component.css']
})
export class ReturnViewComponent implements OnInit {
  key: any;
dwn() {
throw new Error('Method not implemented.');
}
ser() {
this.ngOnInit();
}

  rentDetails: any;

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
sort:string="rent_id";
sortBy:string="rentId"
len: any;
  result: any;
  data: any;
  totalrec: any;



  
  constructor(private adminService:AdminServiceService) { }
  returnbook:FormGroup=new FormGroup({
    bookId:new FormControl(''),
    userId:new FormControl  ('')
  })


  search:FormGroup=new FormGroup({
    inp:new FormControl('')
  })
  ngOnInit(): void {


    this.key=this.search.controls['inp'].value
    console.log(this.key);
    

    if(this.key==""){
          this.adminService.retrunview().subscribe(result=>{
      this.rentDetails=result
      this.count=this.rentDetails.length;
      // console.log(this.rentDetails);
      // console.log(this.count)
    })

    }else{
      this.adminService.rentSearch(this.key,this.page,this.tableSize,this.sort).subscribe(response=>{
        this.rentDetails=response.content;
       console.log(this.rentDetails);
        this.data=this.result;
         this.count=response.totalElements;
         console.log("llllllll",this.count);
        });
    }

    
    
    // this.adminService.retrunview().subscribe(result=>{
    //   this.rentDetails=result
    //   this.count=this.rentDetails.length;
    //   console.log(this.rentDetails);
    //   console.log(this.count)
    // })

    // this.adminService.pagenated(this.page,this.tableSize,this.sort).subscribe(response=>{
    //   this.result=response;
    //   console.log(this.result);
    //   this.data=this.result;
    //   this.totalrec=this.data.length;
      
    // });
  }


  onTableDataChange(event:any) {
    
    console.log(event)
    if(this.key==""){
      this.adminService.pagenated(event,this.tableSize,this.sortBy).subscribe((result=>{
        this.rentDetails=result;
        // console.log(this.data)
      }));        
    }
  else{ this.adminService.rentSearch(this.key,this.page,this.tableSize,this.sort).subscribe(response=>{
    this.rentDetails=response.content;
   console.log(this.rentDetails);
    this.data=this.result;
     this.count=response.totalElements;
     console.log("llllllll",this.count);
    });

  }
}
  

  returnVerify(id: any) {
    let data=id;
    this.returnbook.controls['bookId'].setValue(data.book.bookId);
    this.returnbook.controls['userId'].setValue(data.user.userId)
    this.adminService.returnApprove(data.rentId,this.returnbook.value).subscribe(result=>{
      if(result!=null){
        console.log(result);
        alert(" confirmed");
        window.location.reload();
      }
    })
}
}
