<%@ taglib prefix="common" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<common:pageTemplate title="Project ${projectName} details">

    <ul class="breadcrumb">
          <li><a href="#" contenteditable="true">Home</a> <span class="divider">/</span></li>
          <li><a href="#" contenteditable="true">Library</a> <span class="divider">/</span></li>
          <li class="active" contenteditable="true">Data</li>
    </ul>

    <div class="page-header">
        <h3>Project ${projectName} details</h3>
    </div>
    
    <div class="row">
        <h3>Details</h3>
        <div class="form-group">
           <label for="name">Name:</label>
           <input type="text" id="name" name="name" class="form-control" />
        </div>
        <div class="form-group">
          <label for="description">Description:</label>
          <textarea class="form-control" rows="5" id="description"></textarea>
        </div>
    </div>
    
    <div class="row">
        <h3>Alternatives</h3>
        <div class="list-group">
            <a class="list-group-item clearfix" onclick="alert('Action1 -> Details');">
                Alternative1
                <span class="pull-right">
                    <span class="btn btn-xs btn-default" onclick="alert('Action1 -> Play');">
                        <span class="glyphicon glyphicon-edit"></span>
                    </span>
                    <span class="btn btn-xs btn-default" onclick="alert('Action1 -> Play');">
                        <span class="glyphicon glyphicon-remove"></span>
                    </span>
                </span>
            </a>
            <a class="list-group-item clearfix" onclick="alert('Action2 -> Details');">
                Alternative2
                <span class="pull-right">
                    <span class="btn btn-xs btn-default" onclick="alert('Action1 -> Play');">
                        <span class="glyphicon glyphicon-edit"></span>
                    </span>
                    <span class="btn btn-xs btn-default" onclick="alert('Action1 -> Play');">
                        <span class="glyphicon glyphicon-remove"></span>
                    </span>
                </span>
            </a>
            <a class="list-group-item clearfix" onclick="">
            <span class="pull-right">
                <span class="btn btn-xs btn-default" onclick="alert('Action1 -> Play');">
                    <span class="glyphicon glyphicon-plus"></span>
                </span>
            </span>
            </a>
        </div>
    </div>
    
    <div class="row">
        <h3>Criterias</h3>
        <a class="list-group-item clearfix" onclick="alert('Action1 -> Details');">
            Criteria1
            <span class="pull-right">
                <span class="btn btn-xs btn-default" onclick="alert('Action1 -> Play');">
                    <span class="glyphicon glyphicon-edit"></span>
                </span>
                <span class="btn btn-xs btn-default" onclick="alert('Action1 -> Play');">
                    <span class="glyphicon glyphicon-remove"></span>
                </span>
            </span>
        </a>
        <a class="list-group-item clearfix" onclick="alert('Action2 -> Details');">
            Criteria2
            <span class="pull-right">
                <span class="btn btn-xs btn-default" onclick="alert('Action1 -> Play');">
                    <span class="glyphicon glyphicon-edit"></span>
                </span>
                <span class="btn btn-xs btn-default" onclick="alert('Action1 -> Play');">
                    <span class="glyphicon glyphicon-remove"></span>
                </span>
            </span>
        </a>
        <a class="list-group-item clearfix" onclick="">
            <span class="pull-right">
                <span class="btn btn-xs btn-default" onclick="alert('Action1 -> Play');">
                    <span class="glyphicon glyphicon-plus"></span>
                </span>
            </span>
        </a>
    </div>
    
    <!-- 
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">Voters</div>
            <div class="panel-body">
                <ul>
                    <li>User1</li>
                    <li>Antony Cooper</li>
                    <li>Peter pan</li>
                </ul>
            </div>
        </div>
    </div>
     -->

</common:pageTemplate>