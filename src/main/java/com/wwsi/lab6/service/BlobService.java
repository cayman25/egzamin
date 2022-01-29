package com.wwsi.lab6.service;

import com.azure.core.http.rest.PagedIterable;
import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BlobService {

    public BlobServiceClient blobServiceClient;
    public BlobContainerClient containerClient;

    private String connectionString= System.getenv("BLOB_CONNECTION_STRING");
    private String accessKey=System.getenv("BLOB_ACCESS_KEY");
    private String containerName=System.getenv("BLOB_CONTAINER_NAME");

    @PostConstruct
    private void init(){
        blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
        containerClient = blobServiceClient.getBlobContainerClient(containerName);
    }

    public PagedIterable<BlobItem> getBlobs(){
        return containerClient.listBlobs();
    }
    public void deleteBlob(String id){
        containerClient.getBlobClient(id).delete();
    }

    public void uploadBlob(String id, BinaryData data){
        containerClient.getBlobClient(id).upload(data);
    }

}
