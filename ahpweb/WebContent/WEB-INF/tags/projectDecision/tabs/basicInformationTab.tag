<%@ tag language="java" pageEncoding="UTF-8"%>

<div id="tab-information" class="tab-pane fade in active">
    <h3>Decision Information</h3>
    <p>Please specify information for project decision</p>
    <form>
        <div class="form-group">
            <label for="input-project-name">Decision name</label>
            <input type="text" name="name" class="form-control" id="input-decision-name" placeholder="please enter decision name">
        </div>
        <div class="form-group">
            <label for="input-project-name">Goal</label>
            <textarea name="name" class="form-control" placeholder="please enter project decision goal" rows="2"></textarea>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </form>
</div>