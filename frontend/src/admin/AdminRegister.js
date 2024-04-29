import React from 'react';

export default function AdminRegister(){
        
    async function registerUser(){
            const outputDiv=document.getElementById("outputDiv");
            const form=document.getElementById("registerForm");
            const name=form.elements[0].value;
            const email=form.elements[1].value;
            const password=form.elements[2].value;

            if(name.length === 0 || password.length === 0 || email.length === 0){
                outputDiv.innerHTML="Missing fields";
                return;
            }
            
            const emailCheck=/@gmail.com/.test(email);
            
            if(!emailCheck){
                outputDiv.innerHTML="Email invalid";
                return;
            }
            
            const reqBody=JSON.stringify({name,email,password});
            console.log(reqBody);
            const reqHeaders = {"Content-Type":"application/json"}
            const response=await fetch("http://localhost:8080/admin/register",{
                method:"POST",
                body:reqBody,
                headers:reqHeaders
            })
            const responseText=await response.text();
            
            const status=response.status;
                
            if(status === 200){
                const token=response.headers.get("Token");
                localStorage.setItem("Token",token);
                
            }else if(status === 500){
                outputDiv.innerHTML=status+" "+responseText;
                    
            }else if(status === 401){
                outputDiv.innerHTML=status+" "+responseText;
            }else if(status === 400){
                outputDiv.innerHTML=status+" "+responseText;
                    
            }else{
                outputDiv.innerHTML="Unknown error "+status+" "+responseText;
            }
        }
            
          
        return(
        <div>
             <form id="registerForm" className="form">
            <input id="username" className="input-field" placeholder="username" type="text" required/><br></br>
            <input id="email" className="input-field" placeholder="email" type="email" required/><br></br>
            <input id="password" className="input-field" placeholder="password" type="password" required/><br></br>
            <button type="button" className="button" onClick={()=>{registerUser()}}>submit</button>
        </form>
        <div id="outputDiv"></div>
        </div>
    )
}