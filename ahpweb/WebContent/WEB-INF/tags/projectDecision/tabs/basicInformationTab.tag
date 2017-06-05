<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<div id="tab-information" class="tab-pane fade in active">
    
    <h3>Decision Information</h3>
    <p>Please specify information for project decision</p>
    
    <form method="post" id="project-decision-info-form">
        <div class="form-group">
            <label for="input-decision-goal">Decision goal</label>
            <input type="text" name="goal" class="form-control" id="input-decision-goal" 
                value="${projectDecisionData.goal}"
                placeholder="please enter decision goal">
        </div>
        <div class="form-group">
            <label for="input-project-name">Description</label>
            <textarea name="description" class="form-control" 
                placeholder="please enter project decision description" rows="4">${projectDecisionData.description}</textarea>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Update</button>
        </div>
    </form>
    
</div>