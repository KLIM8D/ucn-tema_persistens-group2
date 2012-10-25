package db;

/**
 * Created: 12-10-2012
 * @version: 0.1
 * Filename: DataAccess.java
 * Description:
 * @changes
 */

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.sql.*;

public class DataAccess
{
    private PreparedStatement _sqlCommand;
    private int _dbType;
    private String _connectionString = "jdbc:sqlserver://IP-ADDR:1433;" +
                                        "databaseName=DBNAME;" +
                                        "user=USERNAME;" +
                                        "password=PASSWORD";
    private boolean _enableSqlMonitor = true;
    private static Connection _con;
    //private DatabaseMetaData dma;
    private static DataAccess _instance;

    public PreparedStatement getSqlCommandText()
    { return _sqlCommand; }
    public void setSqlCommandText(PreparedStatement value)
    { _sqlCommand = value; }

    public void setDbType(int value)
    { _dbType = value; }

    public Connection getCon() throws SQLException
    {
        if(_con == null || isClosed())
            openConnection();
        return _con;
    }


    public static DataAccess getInstance()
    {
        return _instance != null ? _instance : (_instance = new DataAccess());
    }

    private DataAccess()
    {
        _dbType = 1;
        switch(_dbType)
        {
            case 1:
                try
                {
                    //MSSQL Server
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                break;

        }
    }

    public void openConnection()
    {
        try
        {
            _con = DriverManager.getConnection(_connectionString);
            _con.setAutoCommit(true);
            //dma = _con.getMetaData(); // get meta data
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("ConnectionString: " + _connectionString);
        }
    }

    public static void closeConnection()
    {
        try
        {
            _con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void startTransaction()
    {
        try
        {
            _con.setAutoCommit(false);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void commitTransaction()
    {
        try
        {
            _con.setAutoCommit(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void rollbackTransaction()
    {
        try
        {
            _con.rollback();
            _con.setAutoCommit(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public ResultSet callCommandGetResultSet()
    {
        //Benchmark time start
        ThreadMXBean threadmxbean = ManagementFactory.getThreadMXBean();
        long startTime = 0;
        long finishTime;


        if (_enableSqlMonitor)
            startTime =  threadmxbean.getCurrentThreadCpuTime();

        try
        {
            CachedRowSet cachedRowSet = new CachedRowSetImpl();
            if(isClosed())
                openConnection();
            ResultSet newResultSet = _sqlCommand.executeQuery();
            cachedRowSet.populate(newResultSet);
            newResultSet.close();
            closeConnection();
            return cachedRowSet;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (_con != null)
                closeConnection();
        }

        if (_enableSqlMonitor)
        {
            finishTime = threadmxbean.getCurrentThreadCpuTime();
            sqlMonitor((finishTime-startTime), "CallCommandGetResultSet()");
        }

        _sqlCommand = null;

        return null;
    }
    private ResultSet callCommandGetResultSetWithOutMonitor()
    {
        try
        {
            CachedRowSet cachedRowSet = new CachedRowSetImpl();
            if(isClosed())
                openConnection();
            ResultSet newResultSet = _sqlCommand.executeQuery();
            cachedRowSet.populate(newResultSet);
            newResultSet.close();
            closeConnection();
            return cachedRowSet;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (_con != null)
                closeConnection();
        }

        _sqlCommand = null;

        return null;
    }

    public int callCommand()
    {
        int returnVal = 0;

        //Benchmark time start
        ThreadMXBean threadmxbean = ManagementFactory.getThreadMXBean();
        long startTime = 0;
        long finishTime;


        if (_enableSqlMonitor)
            startTime =  threadmxbean.getCurrentThreadCpuTime();

        try
        {
            if(isClosed())
                openConnection();
            returnVal = _sqlCommand.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (_con != null)
                closeConnection();
        }

        if (_enableSqlMonitor)
        {
            finishTime = threadmxbean.getCurrentThreadCpuTime();
            sqlMonitor((finishTime-startTime), "callCommand()");
        }

        _sqlCommand = null;

        return returnVal;
    }

    public String callCommandGetField()
    {
        //Benchmark time start
        ThreadMXBean threadmxbean = ManagementFactory.getThreadMXBean();
        long startTime = 0;
        long finishTime;


        if (_enableSqlMonitor)
            startTime =  threadmxbean.getCurrentThreadCpuTime();

        String returnVal = "";

        try
        {
            ResultSet listData = callCommandGetResultSetWithOutMonitor();
            if (listData != null && listData.next())
            {
                returnVal = listData.getString(1);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(_con != null)
                closeConnection();
        }

        if (_enableSqlMonitor)
        {
            finishTime = threadmxbean.getCurrentThreadCpuTime();
            sqlMonitor((finishTime-startTime), "callCommandGetField()");
        }

        _sqlCommand = null;

        return returnVal;
    }

    public ResultSet callCommandGetRow()
    {
        //Benchmark time start
        ThreadMXBean threadmxbean = ManagementFactory.getThreadMXBean();
        long startTime = 0;
        long finishTime;

        if (_enableSqlMonitor)
            startTime =  threadmxbean.getCurrentThreadCpuTime();

        try
        {
            ResultSet listData = callCommandGetResultSetWithOutMonitor();
            CachedRowSet rowset = new CachedRowSetImpl();
            if (listData != null)
            {
                // && listData.next()
                //listData.first();
                rowset.populate(listData);
                listData.close();
                closeConnection();
                return rowset;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(_con != null)
                closeConnection();
        }

        if (_enableSqlMonitor)
        {
            finishTime = threadmxbean.getCurrentThreadCpuTime();
            sqlMonitor((finishTime - startTime), "callCommandGetField()");
        }

        _sqlCommand = null;

        return null;
    }

    private void sqlMonitor(long sqlExecutionTime, String dalFunction)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Running method:" + dalFunction +  System.getProperty("line.separator"));
        sb.append("Execution time: " + sqlExecutionTime + " ns" +  System.getProperty("line.separator"));
        System.out.println(sb.toString());
    }

    private boolean isClosed()
    {
        Statement stmt;
        ResultSet rs;
        try
        {
            stmt = _con.createStatement();
            rs = stmt.executeQuery("SELECT 1");
            if (rs.next())
                return false; // connection is valid
        }
        catch (SQLException ex)
        {
            //ex.printStackTrace();
            return true;
        }
        return false;
    }
}
