package com.example.demo3.Rowmapper;

import com.example.demo3.models.Customer;
import com.example.demo3.models.ShowCartProduct;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowCartProductRowmapper  implements RowMapper<ShowCartProduct>
{
    public ShowCartProduct mapRow(ResultSet rs, int rn) throws SQLException
    {

        ShowCartProduct showcartproduct = new ShowCartProduct();
        showcartproduct.setProductid(rs.getInt("pid"));
        showcartproduct.setProductname(rs.getString("pname"));
        showcartproduct.setProductdescription(rs.getString("pdescription"));
        showcartproduct.setBrand(rs.getString("pbrand"));
        showcartproduct.setMrp(rs.getFloat("pmrp"));
        showcartproduct.setImagename(rs.getString("pimname"));
        showcartproduct.setQuantity(rs.getInt("cquantity"));
        showcartproduct.setQtyinstock(rs.getInt("pqtyinstock"));
        return  showcartproduct;
    }
}
