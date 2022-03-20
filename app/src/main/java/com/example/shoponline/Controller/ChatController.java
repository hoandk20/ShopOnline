package com.example.shoponline.Controller;

import com.example.shoponline.Model.Account;
import com.example.shoponline.Model.Bill;
import com.example.shoponline.Model.Chat;
import com.example.shoponline.Model.Description;
import com.example.shoponline.Model.Product;

import java.util.ArrayList;

public class ChatController {

    int quantity=0;
    long Productid=0;
    ArrayList<Description> listDes = new ArrayList<>();
    public Chat GetBotMessage(String message,long userId){
        AddDes();
        Chat  c = new Chat();
        String response="";


        if(message.contains("id")){
            if(message.contains("mua")){
                String[] a = message.split(" ");
                for (String temp:a) {
                    if(temp.contains("mua")){
                        temp = temp.replace("mua","");
                        try {
                             quantity = Integer.parseInt(temp);


                        }catch (Exception e){
                            response = "Sai cú pháp xin thử lại!";
                            break;
                        }
                    }
                    if(temp.contains("id")){
                        temp = temp.replace("id","");
                        try {
                            Productid = Integer.parseInt(temp);


                        }catch (Exception e){
                            response = "Sai cú pháp xin thử lại!";
                            break;
                        }
                    }
                }

                response= BuyProduct(Productid,quantity,userId);
            }else{
                String[] a = message.split(" ");
                for (String temp:a) {

                    if(temp.contains("id")){
                        temp = temp.replace("id","");
                        try {
                             Productid = Integer.parseInt(temp);
                            response = GetDescription(Productid);
                        }catch (Exception e){
                            response = "Sai cú pháp xin thử lại!";
                        }
                    }
                }
            }
        }else{
            response = "Sai cú pháp xin thử lại!";
        }

        c.setSenderId("@Bot");
        c.setContent(response);

        return c;
    }

    public String GetDescription(long idProduct){
        String des="";

        Product p = new Product();
        ProductController productController = new ProductController();
        des=GetDesProduct(idProduct);
        des = des+ GetAvailableProduct(idProduct);
        return des;
    }


    public String GetAvailableProduct(long id){

        String des="\n";
        try {
            ProductController productController = new ProductController();
            Product p = productController.GetProductById(Integer.parseInt(id+""));
            des="\n"+p.toString()+"\n";
        }catch (Exception e){
            des="Sản phẩm không tồn tại";
        }

        return  des;
    }



    public String BuyProduct(long productId,int number,long userId){
        String des="\n";
        Product p = new Product();
        try {
            ProductController productController = new ProductController();
            p = productController.GetProductById(Integer.parseInt(productId+""));
            String test ="\n"+p.toString()+"\n";
        }catch (Exception e){
           return  des="";
        }

        Bill b = new Bill();
        b.setProductId(p.getProductId());
        b.setNameProduct(p.getProductName());
        b.setUnitPrice(Double.parseDouble(p.getProductPrice()));
        b.setAccountId(userId);
        b.setQuantity(number);
        b.setImageId(p.getImageId());
        b.setTotalPrice(Double.parseDouble(p.getProductPrice())*number);
        BillController billController = new BillController();
        billController.AddBill(b);
        des= "Bạn đã mua"+b.getQuantity()+" sản phẩm '"+b.getNameProduct()+" với giá: "+b.getTotalPrice()+"" +
                "\nvui lòng kiểm tra lịch sử mua hàng.";
        return  des;
    }

    public String getAddress(String username){
        String des="";
        LoginController loginController = new LoginController();

        Account a = loginController.getAccountByName(username);



        des =
                "Đơn hàng sẽ được giao đến địa chỉ: "+a.getAddress()+", Tên khách hàng: "+a.getUsername()+"" +
                ", Số điện thoại: "+a.getPhone();
        return des;
    }


    public void AddDes(){
        listDes.add(new Description(1,"Màn hình samsung Truyền tải trọn vẹn từng khung hình chân thực, sống động. Công nghệ tấm nền VA của Samsung mang đến tỷ lệ tương phản 3000:1 cho sắc đen thêm sâu thẳm và sắc trắng thêm thuần khiết. Màn hình còn hạn chế tối đa tình trạng rò rỉ ánh sáng từ các góc, giúp hình ảnh luôn rõ nét và hoàn hảo ngay cả khi hiển thị ở những nơi tối nhất hoặc sáng nhất."));
        listDes.add(new Description(2,"Samsung Galaxy S20 FE (8GB|256GB) là phiên bản đặc biệt dành cho những nguời hâm mộ Samsung. Máy sở hữu thiết kế sang trọng, nhiều màu sắc trẻ trung. Màn hình rộng 6.5 inches, hiệu năng mạnh mẽ bởi chip Snapdragon 865. "));
        listDes.add(new Description(3,"Kết hợp cùng những đường nét góc cạnh và được vát phẳng, chiếc iPhone 12 trông cực kỳ nam tính, mạnh mẽ nhưng không kém phần sang trọng. Mặc dù sở hữu một thiết kế quen thuộc nhưng iPhone 12 vẫn có những nét cuốn hút riêng."));
        listDes.add(new Description(4,"Harry Potter và Hòn đá phù thủy của tiểu thuyết gia người Anh J. K. Rowling là một tác phẩm kinh điển của thế giới. Đi sâu vào từng con chữ, ta bắt gặp một thế giới giả tưởng đầy thú vị, lôi cuốn hấp dẫn người đọc theo từng nhịp thở. Để khi gấp sách lại rồi vẫn ám ảnh bởi sự nhiệm màu của cuốn sách"));
        listDes.add(new Description(5,"MSI là thương hiệu không còn xa lạ với người dùng đặc biệt là những game thủ. Laptop MSI có thiết kế hầm hố chuẩn gaming, phục vụ nhu cầu chơi game hoàn hảo như một PC chuyên nghiệp. Để hiểu hơn về thương hiệu laptop này, mời bạn tham khảo bài viết sau."));
    }
    public String GetDesProduct(long productId){
        String des="";
        for (Description d:listDes) {
            if(d.getProductId()==productId){
                des = d.getDes();
                return des;
            }
        }
        if(des.equals("")){
            des="Sản phẩm này hiện không có sẵn";
        }
        return des;
    }
}
