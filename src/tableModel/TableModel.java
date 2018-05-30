package tableModel;

import java.sql.ResultSet;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


public class TableModel extends AbstractTableModel
{
	private String[] ColumnsName;
	private Class[] ColumnClass;
	private int columnCount;
	private List<Object[]> data;
		
	public TableModel(ResultSet resultSet)
	{
		try
		{
		ResultSetMetaData resultSetMetaData= resultSet.getMetaData();
		
		this.columnCount = resultSetMetaData.getColumnCount();
		
		ColumnsName=new String[columnCount];
		ColumnClass=new Class[columnCount];
			for(int i=1;i<=columnCount;i++)
			{
				try
				{
				ColumnsName[i-1]=resultSetMetaData.getColumnName(i);
				ColumnClass[i-1]=Class.forName(resultSetMetaData.getColumnClassName(i));
				
				}
				catch(ClassNotFoundException e)
				{
				e.printStackTrace();
				System.out.println(e.getMessage());
				}
			}
			
			data = new ArrayList<Object[]>();
			
			while(resultSet.next())
			{
				Object[] object=new Object[columnCount];
				for(int i=1;i<=columnCount;i++)
				{
					object[i-1]=resultSet.getObject(i);
				}
			data.add(object);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}	
	
	public int getRowCount()
	{
		return data.size();
	}
	
   	public int getColumnCount()
	{
	 	return columnCount;
	}
	 
	public Object getValueAt(int row, int column)
	{
		return ((Object[])(data.get(row))) [column];
	}
	
	public String getColumnName(int column)
	{
		return this.ColumnsName[column];
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) 
	{
        return false;
	}
	
	public Class getColumnClass(int columnIndex) 
	{
        return this.ColumnClass[columnIndex];
    }
}