import LinkedList from "../utils/LinkedList";
import { useEffect, useState } from "react";

export default function ViewAllPosts(){
    const[token,setToken] = useState(null);
    let postList = new LinkedList();
    
    useEffect(()=>{
        checkToken();
        if(token != null){
            getAllPosts();
        }else{
            console.log("Error token is null");
        }
    },[]);

    function checkToken(){
        const outputDiv = document.getElementById("outputDiv");
        const t = localStorage.getItem("Token");
        
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

    async function getAllPosts(){
        const reqHeaders = {"Token":token};
        const response = await fetch("http://localhost:8080/admin/posts/getAllPosts",{
            method:"GET",
            headers:reqHeaders
        });

        const res = await response.json();

        const list = document.getElementById("post-list");
        const postCountDiv = document.getElementById("post-count");
        postCountDiv.innerHTML = res.length;
        
        for(let i = 0;i < res.length ; i++){
            const post = res[i];
            const postDiv = document.createElement("div");
            const postID = post.id;
            
            const titleLink = document.createElement("a");
            titleLink.href = `localhost:/getPost?postID=${postID}`;
            titleLink.text= post.title;
           
            const updateButton = document.createElement("button");
            const deleteButton = document.createElement("button");
            updateButton.innerText = "update";
            deleteButton.innerText = "delete"; 
            updateButton.onClick = ()=>{
                console.log("update button clicked")
                window.location = `/update_post?postID=${postID}`
            };
            deleteButton.onClick = ()=>{deletePost(postID,post,i)}; 
            
            postDiv.appendChild(titleLink);
            postDiv.appendChild(updateButton);
            postDiv.appendChild(deleteButton);

            const listItem = document.createElement("li");
            listItem.appendChild(postDiv);
            list.appendChild(listItem);

            postList.add(listItem);
        }
    }

    async function deletePost(postID,post,index){
        const reqHeaders = {"Token":token};
        const reqJSON = JSON.stringify(post);
        const res = await fetch(`http://localhost:8080/admin/posts/delete?id=${postID}`,{
            method:"DELETE",
            headers:reqHeaders,
            body:reqJSON
        });

        console.log(res);
        if(res.ok){
          const postListElement = document.getElementById("post-list");
          postListElement.removeChild(postList.get(index));
          postList.removeAt(index); 
        }else{
            const outputDiv = document.getElementById("outputDiv");
            outputDiv.innerHTML = res.status + res.statusText;
        }

    }
}