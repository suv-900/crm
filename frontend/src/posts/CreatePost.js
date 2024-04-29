import React, { useState } from "react";

export default function CreatePost(){
    const[token,setToken] = useState(null);
    
    useEffect(()=>{
        checkToken();
    },[])

    function checkToken(){
        const outputDiv=document.getElementById("outputDiv");    
        let t=localStorage.getItem("Token");
        
        if(t === null){
            outputDiv.innerHTML="Login required";
            const anchorElement=document.createElement("a");
            anchorElement.href="localhost:/login";
            anchorElement.text="login";
            outputDiv.appendChild(anchorElement);
            return;
        }
        setToken(t);
    }

    

    function empty(s){
        return s.length === 0;
    }
    async function createPost(){
        const form= document.getElementById("form");
        const postTitle = form.elements[0].value;
        const postContent = form.elements[1].value;

        if(empty(postTitle) || empty(postContent)){
            const outputDiv = document.getElementById(outputDiv);
            outputDiv.innerHTML = "Bad fields";
            return;
        }

        const post = {
            "title":postTitle,
            "content":postContent
        }
        const json = JSON.stringify(post);
        const reqHeader = {"Token":token};
        const res = await fetch(`http://localhost:8080/admin/posts/update?postID=${postID}`,{
            method:"POST",
            body:json,
            headers:reqHeader
        })

        const response = await res.json();

        console.log(response);

    }
    return (
        <div>
            <form id="form">
                <input id="titleInput" type="text" class="post-title-input"/>
                <input id="contentInput" type="text" class="post-content-input"/>
                <button id="create-button"  type="button" onClick={()=>{createPost()}}>update</button>
            </form>
            <div id="outputDiv"></div>
        </div>
    )             
}