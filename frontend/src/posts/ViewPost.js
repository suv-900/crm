import React, { useEffect } from "react";
import { useParams } from "react-router-dom";

export default function ViewPost(){
    const { id } = useParams();
    useEffect(()=>{
        if(id === null){
            const outputDiv = document.getElementById("outputDiv");
            outputDiv.innerHTML = "ID is null";
            return;
        }
        getPost(id);
    },[])

    async function getPost(id){
        const res = await fetch(`http://localhost:8080/post?postID=${id}`,{
            method:"GET"
        })

        const post = await res.json();
        console.log(post);
        const postTitleDiv = document.getElementById("post-title");
        postTitleDiv.innerHTML = post.title;

        const postContentDiv = document.getElementById("post-content");
        postContentDiv.innerHTML = post.content;
    }

    return(
        <div class="post-container">
            <div id="post-title" class="post-title"></div>
            <div id="post-content" class="post-content"></div>
            <div id="outputDiv" class="outputDiv"></div>
        </div>
    )
}

