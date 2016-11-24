function checkAll(){
   var a = document.getElementsByName ("orderstate");
   if(document.getElementById("orderstate1").checked)
   {
   for(var i=0;i<a.length;i++)
   {
      a[i].checked = true;
   } 
   }else{
   for(var j=0;j<a.length;j++)
   {
     a[j].checked = false;
   }
   }
   }
   function selFirst()
   {
      var o=document.getElementsByName("orderstate");
      var count=0,num=0;
      for(var i=0;i<o.length-1;i++)
      {
        if(o[i+1].checked==true)
        {
          count++;
        }
        if(o[i+1].checked==false)
        {
          num++;
        }
      }
      if(count==o.length-1)
      {
        o[0].checked=true;
      }
      if(num>0)
      {
        if(o[0].checked==true)
        {
          o[0].checked=false;
        }
      }
   }
 var b=document.getElementsByName("paytype");
   function selectAll(){
   if(document.getElementById("paytype1").checked)
   {
   for(i=0;i<b.length;i++)
   {
      b[i].checked=true;
   }
   }else{
   for(j=0;j<b.length;j++)
   {
      b[j].checked=false;
   }
   }
   }
   function selone()
   {
      var o=document.getElementsByName("paytype");
      var count=0,num=0;
      for(var i=0;i<o.length-1;i++)
      {
        if(o[i+1].checked==true)
        {
          count++;
        }
        if(o[i+1].checked==false)
        {
          num++;
        }
      }
      if(count==o.length-1)
      {
        o[0].checked=true;
      }
      if(num>0)
      {
        if(o[0].checked==true)
        {
          o[0].checked=false;
        }
      }
   }