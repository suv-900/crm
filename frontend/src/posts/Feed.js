import React from "react";
import { useEffect } from "react";

export default function Feed(){
    useEffect(()=>{
       fetchPosts(); 
    },[])

    async function fetchPosts(){

        const res = await fetch("http://localhost:8080/post/getFeedPosts",{
            method:"GET"
        })

        const resJSON = await res.json();
        console.log(resJSON);
        const postListDiv = document.getElementById("postsList");
        for(let i = 0;i<resJSON.size();i++){
            const post = resJSON[i];
            
            const postDiv = document.createElement("a");
            postDiv.className = "post-link";
            postDiv.href = `http://localhost:3000/viewpost?id=${post.id}` 
           
            postListDiv.appendChild(postDiv);
        }
    }

    return(
        <div>
            <div id="postsList" className="postsList" ></div>
        </div>
    )
}