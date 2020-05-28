# Example_using_Content_Provider

    *Hi, this is sample application developed using Content Provider . 

    *Before developing application we have question like why so and so concept using for application in mind !. So, i would like to share why content providers
    
    * Content providers provide interprocess communication between two applications. Suppose if two applications want to communicate, 
      they can communicate with help of Content Provider
      Example : Suppose there are two applications like Whatsapp,Contacts application. So, Whatsapp will read all contacts from Contacts
                application using Content Provider. To Read all contacts ,first Whatsapp has to ask Read permission.
                
    * Here in Content Providers we will learn concepts like Content Resolver, Cursor,URI.
         #Content Resolver: So, Every application has indivisual Content Provider .So, which Content Provider communicating with with
         Application there is no data sync.It causes huge problem.To solve this issue Content Resolver are Introduced.Content Resolver 
         provides access to all Content Provider.Its design is important as it allows simple and secure means of accessing other 
         application Content Provider
         #Cursor is used to fetch the data from Content provider.
     
     * I attached the DroidTerms APK ,first we need to download that apk.Because in this application we are accessing Content Provider            of that apk to fetch the data. 
    
