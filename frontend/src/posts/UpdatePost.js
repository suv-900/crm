import { useSearchParams } from "react-router-dom";
import React, { useEffect, useState } from "react";

export default function UpdatePost(){
    const[params,setParams] = useSearchParams();
    const[token,setToken] = useState(null);
    let postID = params.get("postID")
    
    useEffect(()=>{
        checkToken();
        if(postID === null){
            const outputDiv = document.getElementById("outputDiv");
            outputDiv.innerHTML = "bad request postID is null try again";
        }
        getPost();
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

    async function getPost(){
       const res = await fetch(`http://localhost:8080/post?postID=${postID}`,{
        method:"GET"
        }) 

        const resJSON = await res.json();

        let postTitle = resJSON.title;
        let postContent = resJSON.content;

        const titleInput = document.getElementById("titleInput");
        const contentInput = document.getElementById("contentInput");
        titleInput.innerHTML = postTitle;
        contentInput.innerHTML = postContent;
        // createdatInput.innerHTML = postCreatedAt;
    }

    function empty(s){
        return s.length === 0;
    }
    async function updatePost(){
        const updateForm = document.getElementById("updateForm");
        const postTitle = updateForm.elements[0].value;
        const postContent = updateForm.elements[1].value;

        if(empty(postTitle) || empty(postContent)){
            const outputDiv = document.getElementById("outputDiv");
            outputDiv.innerHTML = "Bad fields";
            return;
        }

        const post = {
            "id":postID,
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
            <form id="updateForm">
                <input id="titleInput" type="text" />
                <input id="contentInput" type="text" />
                <button id="update-button"  type="button" onClick={()=>{updatePost()}}>update</button>
            </form>
            <div id="outputDiv"></div>
        </div>
    )             
}