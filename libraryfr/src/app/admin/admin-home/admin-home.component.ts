import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from 'src/app/service/admin/admin-service.service';

import { Chart, registerables } from 'chart.js';
Chart.register(...registerables)

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {
len:any;
i:number=0;
j:number=0;
inlen:any;
label:any []=[];
count:any[]=[];
  mychart: any
  barchart:  any;
  bar: any;
// labels:any[]=[];
  constructor(private adminService:AdminServiceService) { }

  ngOnInit(): void {
    this.adminService.chart().subscribe(res=>{
      console.log(res);
      this.len=res.length;
      this.inlen=res[0].length;
      // console.log(this.len);
      // console.log(this.inlen)
      // // console.log(res[0][0])
      for(this.i=0;this.i<this.len;this.i++){
        for(this.j=0;this.j<this.inlen;this.j++){
          // if(res[this.i][0]==res[this.i][0]){

          // }
          let t=this.i;
          let q=this.j
          // console.log(res[this.i][0]);
        this.count[this.i]= res[this.i][0];
       this.label[this.i]=res[t][1];

          // if(res[t][q+1]!=null){
          // // console.log(res[t][q]);
          
          // console.log(res[t][q+1])
          // q-1;  
          // }
        }
        
      }
      console.log(this.label);
      console.log(this.count);

      this.mychart = new Chart("MyChart", {
        type: 'pie', //this denotes the type of chart
        data: { //values on X-Axis
          labels:this.label, 
           datasets: [
          
            {
              label: "BOOK COUNT",
              data: this.count,
              backgroundColor: ['blue','red','black']
            }  
          ]
        },
        options: {
          aspectRatio:3.3
        } 
      });
    
    
      
    })
  

  
  
  this.adminService.chartbar().subscribe(res=>{
    console.log(res);
    
    this.bar=res;
    this.barchart = new Chart("bar", {
      type: 'bar', //this denotes the type of chart
      data: { //values on X-Axis
        labels:res.label, 
         datasets: [
          {
            label: "RENT COUNT",
            data: res.rentCount,
            backgroundColor: 'red'
          },
          {
            label: "RETURN COUNT",
            data: res.returnCount,
            backgroundColor: 'blue'
          }  
        ]
      },
      options: {
        aspectRatio:3.3
      } 
    });
  





  })
  
  
  }


  

}
