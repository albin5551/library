import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AdminServiceService } from 'src/app/service/admin/admin-service.service';

@Component({
  selector: 'app-viewpg-test',
  templateUrl: './viewpg-test.component.html',
  styleUrls: ['./viewpg-test.component.css']
})
export class ViewpgTestComponent implements OnInit {
authors(arg0: any) {
throw new Error('Method not implemented.');
}


  selectedFiles: any;
  currentFile: any;
  key: any;
  author: any;
ser() {
  this.ngOnInit();
}
  result: any;
  i: any = 0;
  pageNo: any = 1;
  sortBy: any = "book_id";
  sort:string="book_id";
  paginated: any;
  bookDetail: any;
  tableSize: number = 10;
  page: number = 1
  count: any;
  category:any;
  array:number[]=[];
  au:string[]=[];
  constructor(private adminService: AdminServiceService) { }

  search:FormGroup=new FormGroup({
    inp:new FormControl()
  })

  // ab:FormGroup=new FormGroup({
  //   check:new FormControl()
  // })

  ngOnInit(): void {

    this.key=this.search.controls['inp'].value
    console.log('````````````````',this.key);

if(this.key==null){
    this.adminService.pagenate(this.page, this.tableSize, this.sortBy).subscribe(response => {
      this.result = response.content;
      this.count = response.totalElements;
      console.log(this.result);
      console.log(this.count)
    
    });
  }
  else{
    this.adminService.search(this.key,this.page,this.tableSize,this.sort).subscribe(response=>{
          this.result=response.content;
         console.log(this.result);
           this.count=response.totalElements;
           console.log("llllllll",this.count);
           
    });
  }
    this.adminService.categoryView().subscribe(res=>{
      this.category=res;
      console.log(this.category)
    })
    this.adminService.byAuthor().subscribe(res=>{
      this.author=res;
      this.au.push(res);
      
      console.log(this.author)

    })





  }





  onTableDataChange(event: any) {
    if(this.key==null){

    this.adminService.pagenate(event, this.tableSize, this.sortBy).subscribe(
      response => {
        this.result = response.content;
        console.log(this.paginated)
      }
    )
    }
    else{
      this.adminService.search(this.key,this.page,this.tableSize,this.sort).subscribe(response=>{
        this.result=response.content;
       console.log(this.result);
       
  });
    }


  }


  selectFile($event:any) {
    this.selectedFiles=$event.target.files;
     }
     
     upload(): void {
   
       if (this.selectedFiles) {
         const file: File | null = this.selectedFiles.item(0);
   
         if (file) {
           this.currentFile = file;
           this.adminService.upload(this.currentFile).subscribe(res=>{
             console.log(res);
             if(res!==null){
               alert(res.message);
               window.location.reload()
             }
            
           })
   

         }
   
         this.selectedFiles = undefined;
       }
     }

     cate(id: any) {
      console.log('++++++',id);
        this.array.push(id);
      this.adminService.bookByCategory(this.array,this.pageNo,this.tableSize,"").subscribe(res=>{
        this.result = res.content;
        this.count = res.totalElements;
        console.log(this.result);
        console.log(this.count)
      })
      
      }


      newArray : any = [];
      getCheckboxValues(ev:any, data:any) {
        let obj = {
          "order" : data
        }
        
        if(ev.target.checked){
          // Pushing the object into array
          this.newArray.push(data);
    
        }else {
          let el = this.newArray.find((itm:any) => data===data);
          console.log('77777',el)
          
          if(el)
            this.newArray.splice(this.newArray.indexOf(el),1);
            // console.log(this.newArray)
        }
      
        //Duplicates the obj if we uncheck it
        //How to remove the value from array if we uncheck it
        console.log(this.newArray);
      
       
      this.adminService.bookByAuthorlistandcatid(this.newArray,this.newArrays,this.pageNo,this.tableSize,"").subscribe(res=>{
        this.result = res.content;
        this.count = res.totalElements;
        console.log(this.result);
        console.log(this.count)
 
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
    



      newArrays : any = [];
      getCheckbox2Values(ev: any,data: any) {

        let obj = {
          "order" : data
        }
        
        if(ev.target.checked){
          // Pushing the object into array
          this.newArrays.push(data);
    
        }else {
          let el = this.newArrays.find((itm:any) => data===data);
          console.log('77777',el)
          
          if(el)
            this.newArrays.splice(this.newArrays.indexOf(el),1);
            // console.log(this.newArray)
        }
      
        //Duplicates the obj if we uncheck it
        //How to remove the value from array if we uncheck it
        console.log(this.newArray);
      
        
      this.adminService.bookByAuthorlistandcatid(this.newArray,this.newArrays,this.pageNo,this.tableSize,"").subscribe(res=>{
        this.result = res.content;
        this.count = res.totalElements;
        console.log(this.result);
        console.log(this.count)
     
      })

        }

        displayStyle = "none";
  
        openPopup() {
          this.displayStyle = "block";
        }
        closePopup() {
          this.displayStyle = "none";
        }
        

}
