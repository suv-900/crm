import React from "react";
import { useParams } from "react-router-dom";

export default function ViewPost(){
    const { id } = useParams();
    
    async function getPost(postID){
        const res = await fetch(`http://localhost:8080/post/${postID}`,{
            method:"GET"
        })

        const post = await res.json();
        console.log(post);
        const postTitleDiv = document.getElementById("post-title");
        postTitleDiv.innerHTML = post.title;
        
        const postContentDiv = document.getElementById("post-content");
        postContentDiv.innerHTML = post.content;
    }
    getPost(id);

    return(
        <div>
            <div id="post-title" ></div>
            <div id="post-content" ></div>
        </div>
    )
}

