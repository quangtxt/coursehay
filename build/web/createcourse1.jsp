<%@page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dal.*" %>
<%@page import="models.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"> 
        <title>Create a New Course</title>
        <style>
            .create-course-container {
                width: 80%;
                margin: 0 auto;
            }

            .create-course-header {
                text-align: center;
                font-size: 36px;
                margin-top: 50px;
            }

            .create-course-form {
                margin-top: 50px;
            }

            .form-group {
                margin-bottom: 20px;
            }

            label {
                font-size: 18px;
                display: block;
                margin-bottom: 10px;
            }

            input[type="text"], textarea {
                width: 100%;
                padding: 10px;
                font-size: 18px;
                border-radius: 5px;
                border: 1px solid gray;
            }

            textarea {
                height: 200px;
            }

            input[type="submit"] {
                background-color: blue;
                color: white;
                padding: 10px 20px;
                border-radius: 5px;
                cursor: pointer;
                margin-top: 20px;
            }

            #course-price {
                width: 80px;
                height: 20px;
            }
        </style>
    </head>
    <body>
        <div class="create-course-container">
            <div class="create-course-header">
                Create a New Course
            </div>
            <form class="create-course-form" action="create-course1" method="post">
                <input type="hidden" name="name" value="${name}">
                <input type="hidden" name="title" value="${title}">
                <input type="hidden" name="description" value="${desc}">
                <input type="hidden" name="category" value="${cate}">
                <div class="form-group">
                    <label for="course-topic">Course Topic</label>
                    <select id="course-topic" name="topic">
                        <c:forEach items="${listTopic}" var="t">
                            <option value="${t.getTopicId()}">${t.getTopicName()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="course-price">Price</label>
                    <input type="number" id="course-price" name="price" value="0">
                </div>

                <input type="submit" value="Create Course">
            </form>
        </div>
    </body>
</html>

<script>
    const pictureInput = document.getElementById('class_picture');
    const previewElement = document.getElementById('class_picture_preview');
    const previewErrorElement = document.getElementById('class_picture-error');
    console.log(pictureInput, previewElement);
    pictureInput.addEventListener('change', event => {
        const file = event.target.files[0];
        if (!file || file.size > 20 * 1024 * 1024) {
            previewElement.style.backgroundImage = null;
            previewElement.style.paddingTop = null;
            return;
        }
        previewErrorElement.textContent = '';
        const localUrl = `url("` + URL.createObjectURL(file) + `")`;
        previewElement.style.backgroundImage = localUrl;
        previewElement.style.paddingTop = '40%';
    });
    $(document).ready(function () {
        $.validator.addMethod('filesize', function (value, element, param) {
            return this.optional(element) || (element.files[0].size <= param);
        }, 'File size must be less than 20 Mb');
        $('#create-class').validate({
            rules: {
                name: {
                    required: true
                },
                'class_picture': {
                    filesize: 20 * 1024 * 1024
                }
            },
            messages: {
                name: {
                    required: 'Please enter class name'
                }
            }
        });
    });
</script>
